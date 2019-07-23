package com.longfor.gf.lc.account.dao;

import com.longfor.gaia.gfs.data.mybatis.LFMySQLMapper;
import com.longfor.gf.lc.account.dao.entity.Sequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SequenceMapper extends LFMySQLMapper<Sequence> {

    int init(Sequence sequence);

    int incrValue(String sequence);

    Sequence selectSequenceBySeqCode(@Param("seqCode") String seqCode);

    /**
     * Method: 自动生成交易流水号
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 14:46
     * @param
     * @return
     */
    String getTrxNo();
    /**
     * Method: 自动生成交易批次号
     * Description:
     * Author guoguangxiao
     * Data 2019/5/21 14:46
     * @param
     * @return
     */
    String getBatchNo();

}
