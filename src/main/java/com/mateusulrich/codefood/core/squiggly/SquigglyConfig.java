package com.mateusulrich.codefood.core.squiggly;

//@Configuration
//public class SquigglyConfig {
//	@Bean
//	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper) {
//		Squiggly.init (objectMapper, new RequestSquigglyContextProvider ("campos", null));
//
//		var urlPatterns = new String[] {"/pedidos/*", "/restaurantes/*"};
//
//		var filterRegistrationBean = new FilterRegistrationBean<SquigglyRequestFilter> ();
//		filterRegistrationBean.setFilter (new SquigglyRequestFilter ());
//		filterRegistrationBean.setOrder (1);
//		filterRegistrationBean.setUrlPatterns (List.of (urlPatterns));
//
//		return filterRegistrationBean;
//	}
//}
