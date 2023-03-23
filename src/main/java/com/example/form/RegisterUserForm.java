package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザー登録時に入力値を受け取るためのフォームクラス.
 * 
 * @author seiji_kitahara
 *
 */
public class RegisterUserForm {

	/** 性 */
	@NotBlank(message = "必須入力項目です")
	private String lastName;
	/** 名 */
	@NotBlank(message = "名が入力されていません")
	private String firstName;
	/** メールアドレス */
	@Pattern(regexp = "^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$", message="メールアドレスの形式が不正です")
	@NotBlank(message = "メールアドレスが入力されていません")
	private String email;
	/** 郵便番号 */
	@Pattern(regexp = "^\\d{3}\\-?\\d{4}$", message="郵便番号はXXX-XXXXの形式で入力してください")
	@NotBlank(message = "郵便番号")
	private String zipcode;
	/** 住所 */
	@NotBlank(message="住所を入力してください")
	private String address;
	/** 電話番号 */
	@NotBlank(message="電話番号を入力してください")
	@Pattern(regexp="^(070|080|090)-\\d{4}-\\d{4}$", message="電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String telephone;
	/** パスワード */
	@NotBlank(message="パスワードを入力してください")
	@Pattern(regexp="[A-Za-z_0-9]{8,16}", message="パスワードは８文字以上１６文字以内で設定してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message="パスワードを入力してください")
//	@Pattern(regexp="[A-Za-z_0-9]{8,16}")
	private String confirmationPassword;

	@Override
	public String toString() {
		return "RegisterUserForm [lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", zipcode="
				+ zipcode + ", address=" + address + ", telephone=" + telephone + ", password=" + password
				+ ", confirmationPassword=" + confirmationPassword + "]";
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmationPassword() {
		return confirmationPassword;
	}

	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}

}
