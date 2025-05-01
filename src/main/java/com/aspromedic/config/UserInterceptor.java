package com.aspromedic.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.aspromedic.model.Usuario;
import com.aspromedic.model.dao.IUsuarioDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserInterceptor  implements HandlerInterceptor{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user_session_id") != null) {
            Long userId = Long.parseLong(session.getAttribute("user_session_id").toString());
            Optional<Usuario> optionalUser = usuarioDao.findById(userId);
            if (optionalUser.isPresent()) {
                request.setAttribute("user", optionalUser.get());
                request.setAttribute("rol" , optionalUser.get().getRoles());
            } else {
                return false;
            }
        }
        return true;
    }
}
