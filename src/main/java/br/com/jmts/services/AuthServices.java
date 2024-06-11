package br.com.jmts.services;

import br.com.jmts.data.vo.v1.security.AccountCredentialsVO;
import br.com.jmts.data.vo.v1.security.TokenVO;
import br.com.jmts.repositories.UserRepository;
import br.com.jmts.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsVO data){
        try {
            System.out.println("data" + data.getUsername() + data.getPassword());
            var username = data.getUsername();
            var password = data.getPassword();
           // System.out.println("000000000000000000"  );
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("000000000000000000"  );
            var user = repository.findByUsername(username);

            var tokenResponse = new TokenVO();

            if(user != null){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());

            }else{
                throw new UsernameNotFoundException("Username" + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            System.out.println("1111111111111111"  );
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken){
        var user = repository.findByUsername(username);
        var tokenResponse = new TokenVO();
        System.out.println("refresh token" + refreshToken );
        if(user != null){
            tokenResponse = tokenProvider.refreshToken(refreshToken);

        }else{
            throw new UsernameNotFoundException("Username" + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);

    }
}
