package com.udd.udd.service;


import com.udd.udd.model.SystemUser;
import com.udd.udd.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        SystemUser sysUser= systemUserRepository.findByUsername(username);
        if(sysUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else {
            return sysUser;
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            for(String auth: sysUser.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority)
//                    .collect(Collectors.toList())) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(auth));
//            }
//            return new User(sysUser.getUsername(),sysUser.getPassword(),grantedAuthorities);
        }
    }

    // Funkcija pomocu koje korisnik menja svoju lozinku
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
//            LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
//            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

//        LOGGER.debug("Changing password for user '" + username + "'");

        SystemUser systemUser = (SystemUser) loadUserByUsername(username);

        // pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
        // ne zelimo da u bazi cuvamo lozinke u plain text formatu
        systemUser.setPassword(passwordEncoder.encode(newPassword));
        this.systemUserRepository.save(systemUser);

    }
}
