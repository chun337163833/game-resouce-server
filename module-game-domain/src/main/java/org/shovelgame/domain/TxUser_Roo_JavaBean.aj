// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.shovelgame.domain;

import java.util.List;

import org.shovelgame.domain.Role;
import org.shovelgame.domain.TxUser;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

privileged aspect TxUser_Roo_JavaBean {
    
    public Long TxUser.getId() {
        return this.id;
    }
    
    public void TxUser.setId(Long id) {
        this.id = id;
    }
    
    public String TxUser.getUserName() {
        return this.userName;
    }
    
    public void TxUser.setUserName(String userName) {
        this.userName = userName;
    }
    
    public String TxUser.getPassword() {
        return this.password;
    }
    
    public void TxUser.setPassword(String password) {
        this.password = password;
    }
    
    public String TxUser.getFirstName() {
        return this.firstName;
    }
    
    public void TxUser.setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String TxUser.getLastName() {
        return this.lastName;
    }
    
    public void TxUser.setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public Long TxUser.getCellPhone() {
        return this.cellPhone;
    }
    
    public void TxUser.setCellPhone(Long cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    public String TxUser.getEmail() {
        return this.email;
    }
    
    public void TxUser.setEmail(String email) {
        this.email = email;
    }
    
    public List<Role> TxUser.getRoles() {
        return this.roles;
    }
    
    public void TxUser.setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public ShaPasswordEncoder TxUser.getEncoder() {
        return this.encoder;
    }
    
    public void TxUser.setEncoder(ShaPasswordEncoder encoder) {
        this.encoder = encoder;
    }
    
    public String TxUser.getSalt() {
        return this.salt;
    }
    
    public void TxUser.setSalt(String salt) {
        this.salt = salt;
    }
    
    public String TxUser.getMatchPassword() {
        return this.matchPassword;
    }
    
    public void TxUser.setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }
    
}
