package teamNotFound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import teamNotFound.daoimpl.AccountDao;
import teamNotFound.model.Account;

	@Service
	public class MyAppUserDetailsService implements UserDetailsService {
		@Autowired
		private AccountDao dao;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
			Account account = dao.getByUsername(username);
			if(account != null) {
				return User.withUsername(account.getUsername()).password(account.getPassword())
										 .roles(account.getRuolo().getRuolo()).build();
			} else {
				throw new UsernameNotFoundException("Username not found");
			}
		}
	} 

