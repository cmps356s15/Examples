<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE>Your Balance</TITLE>
<LINK REL=STYLESHEET
      HREF="./img/JSP-Styles.css"
      TYPE="text/css">
</HEAD>
<BODY>
<TABLE BORDER=5 ALIGN="CENTER">
  <TR><TH CLASS="TITLE">
      Your Balance</TABLE>
<P>
<IMG SRC="./img/Money.gif" ALIGN="RIGHT">
<UL>
  <LI>First name: ${customer.firstName}
  <LI>Last name: ${customer.lastName}
  <LI>ID: ${customer.id}
  <LI>Balance: QR ${customer.balance}
</UL>      
</BODY></HTML>