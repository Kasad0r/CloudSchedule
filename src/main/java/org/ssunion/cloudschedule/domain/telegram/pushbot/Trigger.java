package org.ssunion.cloudschedule.domain.telegram.pushbot;

import java.io.Serializable;

public enum Trigger implements Serializable {
    NONE, SET_GROUP_STAGE_1, SET_GROUP_STAGE_2, SET_TIME_TO_PUSH
}
