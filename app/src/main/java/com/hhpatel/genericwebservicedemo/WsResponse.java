package com.hhpatel.genericwebservicedemo;

import java.io.Serializable;

public class WsResponse implements Serializable{


    private Member Member;

    private String ResponseText;

    private String ResponseCode;


    public Member getMember ()
    {
        return Member;
    }

    public void setMember (Member Member)
    {
        this.Member = Member;
    }

    public String getResponseText ()
    {
        return ResponseText;
    }

    public void setResponseText (String ResponseText)
    {
        this.ResponseText = ResponseText;
    }

    public String getResponseCode ()
    {
        return ResponseCode;
    }

    public void setResponseCode (String ResponseCode)
    {
        this.ResponseCode = ResponseCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Member = "+Member+", ResponseText = "+ResponseText+", ResponseCode = "+ResponseCode+"]";
    }
}