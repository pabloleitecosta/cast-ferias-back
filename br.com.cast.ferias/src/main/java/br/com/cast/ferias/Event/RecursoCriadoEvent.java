package br.com.cast.ferias.Event;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecursoCriadoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private Long id;
	
	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
		super(source);
		this.response = response;
		this.id       = id;
	}

}
