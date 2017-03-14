package com.example.byg.exam_0120.login;

/**
 * Created by byg on 2017-03-14.
 */

public class ResultModel {
    private String result;
    private String result_code;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultModel{");
        sb.append("result='").append(result).append('\'');
        sb.append(", result_code='").append(result_code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
