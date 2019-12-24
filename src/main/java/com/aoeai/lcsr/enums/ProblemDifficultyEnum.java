package com.aoeai.lcsr.enums;

public enum ProblemDifficultyEnum {
    SIMPLE(1, "简单"),
    MEDIUM(2, "中等"),
    DIFFICULT(3, "困难");

    private int value;

    private String title;

    ProblemDifficultyEnum(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public static ProblemDifficultyEnum getDifficulty(int value){
        for (ProblemDifficultyEnum v : ProblemDifficultyEnum.values()) {
            if (value == v.value) {
                return v;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
