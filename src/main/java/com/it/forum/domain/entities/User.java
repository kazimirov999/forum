package com.it.forum.domain.entities;


import com.it.forum.domain.enumx.Role;
import com.it.forum.utils.EncodeUtil;
import com.it.forum.utils.validators.file.annotation.File;
import com.it.forum.utils.validators.file.annotation.Size;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;


/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Email(message = "Email is not correct")
    private String email;

    @Column(unique = true)
    private String login;
    private String password;
    private boolean enable = true;

    private transient  String passwordTmp;
    private transient String passwordCheck;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime dateRegister;

    @File(type = {com.it.forum.utils.validators.file.Type.JPEG,
            com.it.forum.utils.validators.file.Type.GIF})
    @Size(min = 4000, max = 1000000, message = "Size should be between {min} - {max} byte")
    transient private MultipartFile tmpImg;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    public User() {
        this.dateRegister = DateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (enable != user.enable) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (passwordCheck != null ? !passwordCheck.equals(user.passwordCheck) : user.passwordCheck != null)
            return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + (passwordCheck != null ? passwordCheck.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enable +
                ", passwordCheck='" + passwordCheck + '\'' +
                ", role=" + role +
                ", dateRegister=" + dateRegister +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = EncodeUtil.md5Encryption(password);
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public String getPasswordTmp() {
        return passwordTmp;
    }

    public void setPasswordTmp(String passwordTmp) {
        this.passwordTmp = passwordTmp;
        this.setPassword(passwordTmp);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(DateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public MultipartFile getTmpImg() {
        return tmpImg;
    }

    public void setTmpImg(MultipartFile tmpImg) {

        this.tmpImg = tmpImg;
        if(tmpImg != null){
            try {
                this.photo = tmpImg.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getPhoto() {
        return (photo.length == 0)?null:photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
