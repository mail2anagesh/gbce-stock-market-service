package com.jpmorgan.gbce.stockmarket.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * Class to handle the Rest Api response
 *
 * @param <T>
 */
@JsonInclude(Include.NON_EMPTY)
public class RestApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -86595404654740956L;

    @JsonIgnore
    private StatusCode result;

    private Integer code;
    private String message;
    private T data;

    public RestApiResponse() {
        result = ApiStatusCode.SUCCESS;
    }

    public RestApiResponse(T value) {
        this();
        this.data = value;
    }

    public RestApiResponse(StatusCode rc) {
        this.result = rc;
        setCode(rc.getStatusCode());
        setMessage(rc.getMessage());
    }

    public RestApiResponse(StatusCode rc, T value) {
        this(rc);
        this.data = value;
    }

    public StatusCode getResult() {
        return result;
    }

    public void setResult(StatusCode result) {
        this.result = result;
        setCode(result.getStatusCode());
        setMessage(result.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RestApiResponse<?> other = (RestApiResponse<?>) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return result == other.result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RestResponse [result=");
        builder.append(result);
        builder.append(", code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }

}
