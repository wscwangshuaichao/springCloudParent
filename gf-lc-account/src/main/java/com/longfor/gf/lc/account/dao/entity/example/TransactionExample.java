package com.longfor.gf.lc.account.dao.entity.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransactionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andKeyIdIsNull() {
            addCriterion("key_id is null");
            return (Criteria) this;
        }

        public Criteria andKeyIdIsNotNull() {
            addCriterion("key_id is not null");
            return (Criteria) this;
        }

        public Criteria andKeyIdEqualTo(Integer value) {
            addCriterion("key_id =", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotEqualTo(Integer value) {
            addCriterion("key_id <>", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdGreaterThan(Integer value) {
            addCriterion("key_id >", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("key_id >=", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdLessThan(Integer value) {
            addCriterion("key_id <", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdLessThanOrEqualTo(Integer value) {
            addCriterion("key_id <=", value, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdIn(List<Integer> values) {
            addCriterion("key_id in", values, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotIn(List<Integer> values) {
            addCriterion("key_id not in", values, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdBetween(Integer value1, Integer value2) {
            addCriterion("key_id between", value1, value2, "keyId");
            return (Criteria) this;
        }

        public Criteria andKeyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("key_id not between", value1, value2, "keyId");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNull() {
            addCriterion("trans_no is null");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNotNull() {
            addCriterion("trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransNoEqualTo(String value) {
            addCriterion("trans_no =", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotEqualTo(String value) {
            addCriterion("trans_no <>", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThan(String value) {
            addCriterion("trans_no >", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("trans_no >=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThan(String value) {
            addCriterion("trans_no <", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThanOrEqualTo(String value) {
            addCriterion("trans_no <=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLike(String value) {
            addCriterion("trans_no like", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotLike(String value) {
            addCriterion("trans_no not like", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoIn(List<String> values) {
            addCriterion("trans_no in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotIn(List<String> values) {
            addCriterion("trans_no not in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoBetween(String value1, String value2) {
            addCriterion("trans_no between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotBetween(String value1, String value2) {
            addCriterion("trans_no not between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoIsNull() {
            addCriterion("out_trans_no is null");
            return (Criteria) this;
        }

        public Criteria andOutTransNoIsNotNull() {
            addCriterion("out_trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutTransNoEqualTo(String value) {
            addCriterion("out_trans_no =", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoNotEqualTo(String value) {
            addCriterion("out_trans_no <>", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoGreaterThan(String value) {
            addCriterion("out_trans_no >", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_trans_no >=", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoLessThan(String value) {
            addCriterion("out_trans_no <", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoLessThanOrEqualTo(String value) {
            addCriterion("out_trans_no <=", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoLike(String value) {
            addCriterion("out_trans_no like", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoNotLike(String value) {
            addCriterion("out_trans_no not like", value, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoIn(List<String> values) {
            addCriterion("out_trans_no in", values, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoNotIn(List<String> values) {
            addCriterion("out_trans_no not in", values, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoBetween(String value1, String value2) {
            addCriterion("out_trans_no between", value1, value2, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andOutTransNoNotBetween(String value1, String value2) {
            addCriterion("out_trans_no not between", value1, value2, "outTransNo");
            return (Criteria) this;
        }

        public Criteria andAccOutIsNull() {
            addCriterion("acc_out is null");
            return (Criteria) this;
        }

        public Criteria andAccOutIsNotNull() {
            addCriterion("acc_out is not null");
            return (Criteria) this;
        }

        public Criteria andAccOutEqualTo(String value) {
            addCriterion("acc_out =", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutNotEqualTo(String value) {
            addCriterion("acc_out <>", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutGreaterThan(String value) {
            addCriterion("acc_out >", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutGreaterThanOrEqualTo(String value) {
            addCriterion("acc_out >=", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutLessThan(String value) {
            addCriterion("acc_out <", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutLessThanOrEqualTo(String value) {
            addCriterion("acc_out <=", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutLike(String value) {
            addCriterion("acc_out like", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutNotLike(String value) {
            addCriterion("acc_out not like", value, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutIn(List<String> values) {
            addCriterion("acc_out in", values, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutNotIn(List<String> values) {
            addCriterion("acc_out not in", values, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutBetween(String value1, String value2) {
            addCriterion("acc_out between", value1, value2, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutNotBetween(String value1, String value2) {
            addCriterion("acc_out not between", value1, value2, "accOut");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtIsNull() {
            addCriterion("acc_out_amt is null");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtIsNotNull() {
            addCriterion("acc_out_amt is not null");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtEqualTo(BigDecimal value) {
            addCriterion("acc_out_amt =", value, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtNotEqualTo(BigDecimal value) {
            addCriterion("acc_out_amt <>", value, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtGreaterThan(BigDecimal value) {
            addCriterion("acc_out_amt >", value, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("acc_out_amt >=", value, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtLessThan(BigDecimal value) {
            addCriterion("acc_out_amt <", value, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("acc_out_amt <=", value, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtIn(List<BigDecimal> values) {
            addCriterion("acc_out_amt in", values, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtNotIn(List<BigDecimal> values) {
            addCriterion("acc_out_amt not in", values, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("acc_out_amt between", value1, value2, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccOutAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("acc_out_amt not between", value1, value2, "accOutAmt");
            return (Criteria) this;
        }

        public Criteria andAccInIsNull() {
            addCriterion("acc_in is null");
            return (Criteria) this;
        }

        public Criteria andAccInIsNotNull() {
            addCriterion("acc_in is not null");
            return (Criteria) this;
        }

        public Criteria andAccInEqualTo(String value) {
            addCriterion("acc_in =", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInNotEqualTo(String value) {
            addCriterion("acc_in <>", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInGreaterThan(String value) {
            addCriterion("acc_in >", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInGreaterThanOrEqualTo(String value) {
            addCriterion("acc_in >=", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInLessThan(String value) {
            addCriterion("acc_in <", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInLessThanOrEqualTo(String value) {
            addCriterion("acc_in <=", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInLike(String value) {
            addCriterion("acc_in like", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInNotLike(String value) {
            addCriterion("acc_in not like", value, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInIn(List<String> values) {
            addCriterion("acc_in in", values, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInNotIn(List<String> values) {
            addCriterion("acc_in not in", values, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInBetween(String value1, String value2) {
            addCriterion("acc_in between", value1, value2, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInNotBetween(String value1, String value2) {
            addCriterion("acc_in not between", value1, value2, "accIn");
            return (Criteria) this;
        }

        public Criteria andAccInAmtIsNull() {
            addCriterion("acc_in_amt is null");
            return (Criteria) this;
        }

        public Criteria andAccInAmtIsNotNull() {
            addCriterion("acc_in_amt is not null");
            return (Criteria) this;
        }

        public Criteria andAccInAmtEqualTo(BigDecimal value) {
            addCriterion("acc_in_amt =", value, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtNotEqualTo(BigDecimal value) {
            addCriterion("acc_in_amt <>", value, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtGreaterThan(BigDecimal value) {
            addCriterion("acc_in_amt >", value, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("acc_in_amt >=", value, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtLessThan(BigDecimal value) {
            addCriterion("acc_in_amt <", value, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("acc_in_amt <=", value, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtIn(List<BigDecimal> values) {
            addCriterion("acc_in_amt in", values, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtNotIn(List<BigDecimal> values) {
            addCriterion("acc_in_amt not in", values, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("acc_in_amt between", value1, value2, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andAccInAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("acc_in_amt not between", value1, value2, "accInAmt");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNull() {
            addCriterion("business_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIsNotNull() {
            addCriterion("business_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeEqualTo(String value) {
            addCriterion("business_type =", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotEqualTo(String value) {
            addCriterion("business_type <>", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThan(String value) {
            addCriterion("business_type >", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_type >=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThan(String value) {
            addCriterion("business_type <", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLessThanOrEqualTo(String value) {
            addCriterion("business_type <=", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeLike(String value) {
            addCriterion("business_type like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotLike(String value) {
            addCriterion("business_type not like", value, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeIn(List<String> values) {
            addCriterion("business_type in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotIn(List<String> values) {
            addCriterion("business_type not in", values, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeBetween(String value1, String value2) {
            addCriterion("business_type between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andBusinessTypeNotBetween(String value1, String value2) {
            addCriterion("business_type not between", value1, value2, "businessType");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNull() {
            addCriterion("trans_type is null");
            return (Criteria) this;
        }

        public Criteria andTransTypeIsNotNull() {
            addCriterion("trans_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransTypeEqualTo(String value) {
            addCriterion("trans_type =", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotEqualTo(String value) {
            addCriterion("trans_type <>", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThan(String value) {
            addCriterion("trans_type >", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeGreaterThanOrEqualTo(String value) {
            addCriterion("trans_type >=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThan(String value) {
            addCriterion("trans_type <", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLessThanOrEqualTo(String value) {
            addCriterion("trans_type <=", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeLike(String value) {
            addCriterion("trans_type like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotLike(String value) {
            addCriterion("trans_type not like", value, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeIn(List<String> values) {
            addCriterion("trans_type in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotIn(List<String> values) {
            addCriterion("trans_type not in", values, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeBetween(String value1, String value2) {
            addCriterion("trans_type between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andTransTypeNotBetween(String value1, String value2) {
            addCriterion("trans_type not between", value1, value2, "transType");
            return (Criteria) this;
        }

        public Criteria andYyyyIsNull() {
            addCriterion("yyyy is null");
            return (Criteria) this;
        }

        public Criteria andYyyyIsNotNull() {
            addCriterion("yyyy is not null");
            return (Criteria) this;
        }

        public Criteria andYyyyEqualTo(Integer value) {
            addCriterion("yyyy =", value, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyNotEqualTo(Integer value) {
            addCriterion("yyyy <>", value, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyGreaterThan(Integer value) {
            addCriterion("yyyy >", value, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyGreaterThanOrEqualTo(Integer value) {
            addCriterion("yyyy >=", value, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyLessThan(Integer value) {
            addCriterion("yyyy <", value, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyLessThanOrEqualTo(Integer value) {
            addCriterion("yyyy <=", value, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyIn(List<Integer> values) {
            addCriterion("yyyy in", values, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyNotIn(List<Integer> values) {
            addCriterion("yyyy not in", values, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyBetween(Integer value1, Integer value2) {
            addCriterion("yyyy between", value1, value2, "yyyy");
            return (Criteria) this;
        }

        public Criteria andYyyyNotBetween(Integer value1, Integer value2) {
            addCriterion("yyyy not between", value1, value2, "yyyy");
            return (Criteria) this;
        }

        public Criteria andMmIsNull() {
            addCriterion("mm is null");
            return (Criteria) this;
        }

        public Criteria andMmIsNotNull() {
            addCriterion("mm is not null");
            return (Criteria) this;
        }

        public Criteria andMmEqualTo(Integer value) {
            addCriterion("mm =", value, "mm");
            return (Criteria) this;
        }

        public Criteria andMmNotEqualTo(Integer value) {
            addCriterion("mm <>", value, "mm");
            return (Criteria) this;
        }

        public Criteria andMmGreaterThan(Integer value) {
            addCriterion("mm >", value, "mm");
            return (Criteria) this;
        }

        public Criteria andMmGreaterThanOrEqualTo(Integer value) {
            addCriterion("mm >=", value, "mm");
            return (Criteria) this;
        }

        public Criteria andMmLessThan(Integer value) {
            addCriterion("mm <", value, "mm");
            return (Criteria) this;
        }

        public Criteria andMmLessThanOrEqualTo(Integer value) {
            addCriterion("mm <=", value, "mm");
            return (Criteria) this;
        }

        public Criteria andMmIn(List<Integer> values) {
            addCriterion("mm in", values, "mm");
            return (Criteria) this;
        }

        public Criteria andMmNotIn(List<Integer> values) {
            addCriterion("mm not in", values, "mm");
            return (Criteria) this;
        }

        public Criteria andMmBetween(Integer value1, Integer value2) {
            addCriterion("mm between", value1, value2, "mm");
            return (Criteria) this;
        }

        public Criteria andMmNotBetween(Integer value1, Integer value2) {
            addCriterion("mm not between", value1, value2, "mm");
            return (Criteria) this;
        }

        public Criteria andDdIsNull() {
            addCriterion("dd is null");
            return (Criteria) this;
        }

        public Criteria andDdIsNotNull() {
            addCriterion("dd is not null");
            return (Criteria) this;
        }

        public Criteria andDdEqualTo(Integer value) {
            addCriterion("dd =", value, "dd");
            return (Criteria) this;
        }

        public Criteria andDdNotEqualTo(Integer value) {
            addCriterion("dd <>", value, "dd");
            return (Criteria) this;
        }

        public Criteria andDdGreaterThan(Integer value) {
            addCriterion("dd >", value, "dd");
            return (Criteria) this;
        }

        public Criteria andDdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dd >=", value, "dd");
            return (Criteria) this;
        }

        public Criteria andDdLessThan(Integer value) {
            addCriterion("dd <", value, "dd");
            return (Criteria) this;
        }

        public Criteria andDdLessThanOrEqualTo(Integer value) {
            addCriterion("dd <=", value, "dd");
            return (Criteria) this;
        }

        public Criteria andDdIn(List<Integer> values) {
            addCriterion("dd in", values, "dd");
            return (Criteria) this;
        }

        public Criteria andDdNotIn(List<Integer> values) {
            addCriterion("dd not in", values, "dd");
            return (Criteria) this;
        }

        public Criteria andDdBetween(Integer value1, Integer value2) {
            addCriterion("dd between", value1, value2, "dd");
            return (Criteria) this;
        }

        public Criteria andDdNotBetween(Integer value1, Integer value2) {
            addCriterion("dd not between", value1, value2, "dd");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNull() {
            addCriterion("modify_user is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNotNull() {
            addCriterion("modify_user is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserEqualTo(String value) {
            addCriterion("modify_user =", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotEqualTo(String value) {
            addCriterion("modify_user <>", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThan(String value) {
            addCriterion("modify_user >", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThanOrEqualTo(String value) {
            addCriterion("modify_user >=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThan(String value) {
            addCriterion("modify_user <", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThanOrEqualTo(String value) {
            addCriterion("modify_user <=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLike(String value) {
            addCriterion("modify_user like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotLike(String value) {
            addCriterion("modify_user not like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserIn(List<String> values) {
            addCriterion("modify_user in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotIn(List<String> values) {
            addCriterion("modify_user not in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserBetween(String value1, String value2) {
            addCriterion("modify_user between", value1, value2, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotBetween(String value1, String value2) {
            addCriterion("modify_user not between", value1, value2, "modifyUser");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}