package zawkin.asuna.kunuz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.entity.ProfileEntity;
import zawkin.asuna.kunuz.enums.ProfileStatus;
import zawkin.asuna.kunuz.repository.ProfileRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProfileEntity profile = profileRepository.findByEmailOrPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(profile.getPassword()) // Assuming password is already encoded
                .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_" + profile.getRole().name())))
                .disabled(!profile.getStatus().equals(ProfileStatus.ACTIVE))
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .build();
    }
}
