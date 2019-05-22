package com.example.thefeast;

public class Member {

    String memberId;
    String memberName;
    String memberAddress;
    String memberPhone;





    Member(){

    }


    public Member(String memberId, String memberName, String memberAddress, String memberPhone) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberPhone = memberPhone;
    }


    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public String getMemberPhone() {
        return memberPhone;
    }
}
