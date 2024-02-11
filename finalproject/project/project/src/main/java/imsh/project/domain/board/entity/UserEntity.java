package imsh.project.domain.board.entity;

import imsh.project.domain.board.dto.request.auth.SignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    // 사용자 이메일
    @Column(name="user_email")
    private String email;

    // 사용자 비밀번호
    @Column(name="user_password")
    private String password;

    // 사용자 닉네임
    @Column(name="user_nickname")
    private String nickname;

    // 사용자 휴대전화 번호
    @Column(name="user_tel_number")
    private String telNumber;

    // 사용자 주소
    @Column(name="user_address")
    private String address;

    // 사용자 상세 주소
    @Column(name="user_address_detail")
    private String addressDetail;

    // 사용자 프로필 사진
    @Column(name="user_profileImage")
    private String profileImage;

    @Column(name="user_agreePersonal")
    private boolean agreePersonal;

    @OneToMany(mappedBy = "userEntity")
    private List<Board> boards;

    public UserEntity(SignUpRequestDto dto){
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.agreePersonal = dto.getAgreedPersonal();
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }
}
