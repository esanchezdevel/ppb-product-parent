package com.digital.payments.product.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.digital.payments.product.jpa.entity.Visit;
import com.digital.payments.product.jpa.repository.VisitRepository;

@Component
public class StatsInterceptor implements HandlerInterceptor {

	@Autowired
	private VisitRepository visitRepository;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Visit visit = new Visit();
		visit.setUserIp(request.getLocalAddr());
		visit.setRoute(request.getRequestURI());
		visit.setMethod(request.getMethod());
		visit.setCountry("es");
		visit.setUserAgent(request.getHeader("user-agent"));
		visitRepository.save(visit);
    	
        return true;
    }
}
