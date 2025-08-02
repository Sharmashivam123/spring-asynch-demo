package com.ss.spring_asynch_demo.records;

import java.util.List;

public record EmailRequest(
        List<String> to,
        String subject,
        String body
) {

}
