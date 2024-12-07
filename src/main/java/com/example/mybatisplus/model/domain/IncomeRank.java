package com.example.mybatisplus.model.domain;

/**
 * @author gxy
 * @date 2022/3/9$
 */

public class IncomeRank {
    public float allIncome;
    public float monthIncome;
    public float yearIncome;
    public float incomeRank;
    public int favoriteNum;

    public float getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(float allIncome) {
        this.allIncome = allIncome;
    }

    public float getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(float monthIncome) {
        this.monthIncome = monthIncome;
    }

    public float getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(float yearIncome) {
        this.yearIncome = yearIncome;
    }

    public float getIncomeRank() {
        return incomeRank;
    }

    public void setIncomeRank(float incomeRank) {
        this.incomeRank = incomeRank;
    }

    public int getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(int favoriteNum) {
        this.favoriteNum = favoriteNum;
    }
}
