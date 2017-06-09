package com.havrylyuk.newandroidarchitecture.data.remote.model;

import android.support.annotation.Nullable;

/**
 * Created by Igor Havrylyuk on 08.03.2017.
 */

public class ApiResponse {

    @Nullable
    private Status status;

    public ApiResponse() {
    }

    @Nullable
    public Status getStatus() {
        return status;
    }

    public static class  Status {

        private String message;
        private int value;

        public Status() {
        }

        public String getMessage() {
            return message;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return  message + ' ' + ", ErrorCode:" + value ;
        }
    }

}
