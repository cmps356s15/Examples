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
<CENTER><IMG SRC="./img/Sailing.gif"></CENTER>
<BR CLEAR="ALL">
It is an honor to serve you, 
${customer.firstName} ${customer.lastName}!
<P>
Since you are one of our most valued customers, we would like
to offer you the opportunity to spend a mere fraction of your
QR ${customer.balance}
on a boat worthy of your status. Please visit our boat store for 
more information.      
</BODY></HTML>