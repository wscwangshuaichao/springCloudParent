package com.longfor.gf.lc.account.service.impl;

import com.longfor.gf.lc.account.dao.SequenceMapper;
import com.longfor.gf.lc.account.dao.entity.Sequence;
import com.longfor.gf.lc.account.exception.ServiceException;
import com.longfor.gf.lc.account.service.RedisLockService;
import com.longfor.gf.lc.account.service.SequenceService;
import com.longfor.gf.lc.account.util.CodeEnum;
import com.longfor.gf.lc.account.util.Constants;
import com.longfor.gf.lc.account.util.DateUtil;
import com.longfor.gf.lc.account.util.TypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class SequenceServiceImpl implements SequenceService {

    @Resource
    private SequenceMapper sequenceMapper;
    @Resource
    private RedisLockService redisLockService;

    @Override
    public int nextval(String seqCode) {
        if(sequenceMapper.incrValue(seqCode) > 0){
            return currval(seqCode);
        }
        return 0;
    }

    @Override
    public int currval(String seqCode) {
        Sequence sequence = sequenceMapper.selectSequenceBySeqCode(seqCode);
        return sequence == null ? null : sequence.getCurValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createAccNo(String accountType) throws ServiceException {

        String lockKey = accountType;
        int nextval = 0;
        try{
            if(redisLockService.lock(lockKey,2000,20000)){
                String seqCode = Constants.REQ_PREFIX + accountType;
                Sequence sequence = sequenceMapper.selectSequenceBySeqCode(seqCode);
                if(sequence == null){
                    sequence = new Sequence();
                    sequence.setSeqCode(seqCode);
                    sequence.setSeqName(TypeEnum.Account.getName(accountType));
                    sequence.setCurValue(0);
                    sequence.setIncrValue(1);
                    sequence.setCreateUser("system");
                    sequence.setModifyUser("system");
                    sequenceMapper.init(sequence);
                }
                nextval = nextval(seqCode);
            }
        }catch(Exception e){
            log.error("[Exception] - [SequenceService] - method:[createAccNo] - errorMsg:[{}]", e.getMessage(),e);
            throw new ServiceException(CodeEnum.BizCode.SYS_ERROR);
        }finally {
            redisLockService.unlock(lockKey);
        }
        if(nextval == 0){
            throw new ServiceException(CodeEnum.BizCode.SEQ_CREATE_ERROR);
        }
        return accountType + "-"+ DateUtil.dateToString(new Date(), DateUtil.YYYYMMDD) + "-"+ String.format("%06d", nextval);

    }

    @Override
    public String createBatchNo() {
        return sequenceMapper.getBatchNo();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String getTrxNo() {
        return sequenceMapper.getTrxNo();
    }


}
