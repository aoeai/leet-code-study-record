package com.aoeai.lcsr.enums;

/**
 * 刷题状态
 */
public enum  ProblemStatusEnum {
    NOT_ANSWERED(1, "未解答"),
    ANSWERED(2, "已解答"),
    TRIED(3, "尝试过"),
    SLIGHTLY_UNDERSTAND(4, "略懂"),
    MASTER(5, "掌握"),
    MASTERY(6, "精通");

    private int value;

    private String title;

    ProblemStatusEnum(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    /**
     * 验证状态是否正确
     * @param status
     * @return true 正确
     */
    public final static boolean check(int status){
        for (ProblemStatusEnum ps : values()) {
            if (ps.value == status) {
                return true;
            }
        }
        return false;
    }

    public final static String title(Integer status){
        for (ProblemStatusEnum ps : values()) {
            if (ps.value == status) {
                return ps.title;
            }
        }
        return "未知";
    }

}
