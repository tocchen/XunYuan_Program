package top.tocchen.enums;

import lombok.AllArgsConstructor;

/**
 * @author tocchen
 * @date 2023/2/20 15:04
 * @since jdk 1.8
 **/
@AllArgsConstructor
public enum UserCVUpdateType {
    ADVANTAGE("advantage"),
    EDUCATIONAL("educational"),
    INFO("info"),
    PROJECT_HISTORY("projectHistory"),
    WORK_HISTORY("workHistory");

    private final String value;

    public String getValue() {
        return value;
    }
}
