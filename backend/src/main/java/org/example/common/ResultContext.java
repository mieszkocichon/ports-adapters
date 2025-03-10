package org.example.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@Setter
@Getter
public class ResultContext<T> {
    private String methodToInvoke;
    private DeferredResult<T> deferredResult;
    private Map<String, Object> args;
}
