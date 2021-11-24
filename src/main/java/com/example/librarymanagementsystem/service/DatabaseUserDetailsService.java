package  com.example.librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.librarymanagementsystem.entity.Privilege;
import com.example.librarymanagementsystem.entity.Role;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.repository.RoleRepository;
import com.example.librarymanagementsystem.repository.UserRepository;

@Service("databaseUserDetailsService")
@Transactional
public class DatabaseUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Override
    public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
 
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
              " ", " ", true, true, true, true, 
              getAuthorities(Arrays.asList(
                roleRepository.findByName("ROLE_USER"))));
        }
 
        Collection<? extends GrantedAuthority> authorities = getAuthorities(user.getRoles());
        
        return new org.springframework.security.core.userdetails.User(
          user.getEmail(), user.getPassword(), user.isEnabled(), true, true, 
          true, authorities);
    }
 
    private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Role> roles) {
 
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.addAll(getGrantedAuthorities(roles));
        authorities.addAll(getGrantedAuthorities(getPrivileges(roles)));
        
        return authorities;
    }
 
    private List<String> getPrivileges(Collection<Role> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }
 
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
        	authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}