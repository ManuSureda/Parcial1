package com.utn.TPFinal.session;

import com.utn.TPFinal.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    String token;
    User loggedUser;
    Date lastAction;
}
