package com.longfor.gf.lc.account.dao.entity.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QtAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QtAccountExample() {
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

        public Criteria andAccNoIsNull() {
            addCriterion("acc_no is null");
            return (Criteria) this;
        }

        public Criteria andAccNoIsNotNull() {
            addCriterion("acc_no is not null");
            return (Criteria) this;
        }

        public Criteria andAccNoEqualTo(String value) {
            addCriterion("acc_no =", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotEqualTo(String value) {
            addCriterion("acc_no <>", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoGreaterThan(String value) {
            addCriterion("acc_no >", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoGreaterThanOrEqualTo(String value) {
            addCriterion("acc_no >=", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLessThan(String value) {
            addCriterion("acc_no <", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLessThanOrEqualTo(String value) {
            addCriterion("acc_no <=", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoLike(String value) {
            addCriterion("acc_no like", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotLike(String value) {
            addCriterion("acc_no not like", value, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoIn(List<String> values) {
            addCriterion("acc_no in", values, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotIn(List<String> values) {
            addCriterion("acc_no not in", values, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoBetween(String value1, String value2) {
            addCriterion("acc_no between", value1, value2, "accNo");
            return (Criteria) this;
        }

        public Criteria andAccNoNotBetween(String value1, String value2) {
            addCriterion("acc_no not between", value1, value2, "accNo");
            return (Criteria) this;
        }

        public Criteria andQtCodeIsNull() {
            addCriterion("qt_code is null");
            return (Criteria) this;
        }

        public Criteria andQtCodeIsNotNull() {
            addCriterion("qt_code is not null");
            return (Criteria) this;
        }

        public Criteria andQtCodeEqualTo(String value) {
            addCriterion("qt_code =", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeNotEqualTo(String value) {
            addCriterion("qt_code <>", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeGreaterThan(String value) {
            addCriterion("qt_code >", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeGreaterThanOrEqualTo(String value) {
            addCriterion("qt_code >=", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeLessThan(String value) {
            addCriterion("qt_code <", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeLessThanOrEqualTo(String value) {
            addCriterion("qt_code <=", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeLike(String value) {
            addCriterion("qt_code like", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeNotLike(String value) {
            addCriterion("qt_code not like", value, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeIn(List<String> values) {
            addCriterion("qt_code in", values, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeNotIn(List<String> values) {
            addCriterion("qt_code not in", values, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeBetween(String value1, String value2) {
            addCriterion("qt_code between", value1, value2, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtCodeNotBetween(String value1, String value2) {
            addCriterion("qt_code not between", value1, value2, "qtCode");
            return (Criteria) this;
        }

        public Criteria andQtNameIsNull() {
            addCriterion("qt_name is null");
            return (Criteria) this;
        }

        public Criteria andQtNameIsNotNull() {
            addCriterion("qt_name is not null");
            return (Criteria) this;
        }

        public Criteria andQtNameEqualTo(String value) {
            addCriterion("qt_name =", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameNotEqualTo(String value) {
            addCriterion("qt_name <>", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameGreaterThan(String value) {
            addCriterion("qt_name >", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameGreaterThanOrEqualTo(String value) {
            addCriterion("qt_name >=", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameLessThan(String value) {
            addCriterion("qt_name <", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameLessThanOrEqualTo(String value) {
            addCriterion("qt_name <=", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameLike(String value) {
            addCriterion("qt_name like", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameNotLike(String value) {
            addCriterion("qt_name not like", value, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameIn(List<String> values) {
            addCriterion("qt_name in", values, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameNotIn(List<String> values) {
            addCriterion("qt_name not in", values, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameBetween(String value1, String value2) {
            addCriterion("qt_name between", value1, value2, "qtName");
            return (Criteria) this;
        }

        public Criteria andQtNameNotBetween(String value1, String value2) {
            addCriterion("qt_name not between", value1, value2, "qtName");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtIsNull() {
            addCriterion("balance_amt is null");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtIsNotNull() {
            addCriterion("balance_amt is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtEqualTo(BigDecimal value) {
            addCriterion("balance_amt =", value, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtNotEqualTo(BigDecimal value) {
            addCriterion("balance_amt <>", value, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtGreaterThan(BigDecimal value) {
            addCriterion("balance_amt >", value, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_amt >=", value, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtLessThan(BigDecimal value) {
            addCriterion("balance_amt <", value, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance_amt <=", value, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtIn(List<BigDecimal> values) {
            addCriterion("balance_amt in", values, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtNotIn(List<BigDecimal> values) {
            addCriterion("balance_amt not in", values, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_amt between", value1, value2, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andBalanceAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance_amt not between", value1, value2, "balanceAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtIsNull() {
            addCriterion("frozen_amt is null");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtIsNotNull() {
            addCriterion("frozen_amt is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtEqualTo(BigDecimal value) {
            addCriterion("frozen_amt =", value, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtNotEqualTo(BigDecimal value) {
            addCriterion("frozen_amt <>", value, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtGreaterThan(BigDecimal value) {
            addCriterion("frozen_amt >", value, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_amt >=", value, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtLessThan(BigDecimal value) {
            addCriterion("frozen_amt <", value, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("frozen_amt <=", value, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtIn(List<BigDecimal> values) {
            addCriterion("frozen_amt in", values, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtNotIn(List<BigDecimal> values) {
            addCriterion("frozen_amt not in", values, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_amt between", value1, value2, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andFrozenAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("frozen_amt not between", value1, value2, "frozenAmt");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeIsNull() {
            addCriterion("last_zero_time is null");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeIsNotNull() {
            addCriterion("last_zero_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeEqualTo(Date value) {
            addCriterion("last_zero_time =", value, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeNotEqualTo(Date value) {
            addCriterion("last_zero_time <>", value, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeGreaterThan(Date value) {
            addCriterion("last_zero_time >", value, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_zero_time >=", value, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeLessThan(Date value) {
            addCriterion("last_zero_time <", value, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_zero_time <=", value, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeIn(List<Date> values) {
            addCriterion("last_zero_time in", values, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeNotIn(List<Date> values) {
            addCriterion("last_zero_time not in", values, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeBetween(Date value1, Date value2) {
            addCriterion("last_zero_time between", value1, value2, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andLastZeroTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_zero_time not between", value1, value2, "lastZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeIsNull() {
            addCriterion("this_zero_time is null");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeIsNotNull() {
            addCriterion("this_zero_time is not null");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeEqualTo(Date value) {
            addCriterion("this_zero_time =", value, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeNotEqualTo(Date value) {
            addCriterion("this_zero_time <>", value, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeGreaterThan(Date value) {
            addCriterion("this_zero_time >", value, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("this_zero_time >=", value, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeLessThan(Date value) {
            addCriterion("this_zero_time <", value, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeLessThanOrEqualTo(Date value) {
            addCriterion("this_zero_time <=", value, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeIn(List<Date> values) {
            addCriterion("this_zero_time in", values, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeNotIn(List<Date> values) {
            addCriterion("this_zero_time not in", values, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeBetween(Date value1, Date value2) {
            addCriterion("this_zero_time between", value1, value2, "thisZeroTime");
            return (Criteria) this;
        }

        public Criteria andThisZeroTimeNotBetween(Date value1, Date value2) {
            addCriterion("this_zero_time not between", value1, value2, "thisZeroTime");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "delete");
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