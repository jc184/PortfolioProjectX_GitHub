<%-- 
    Document   : index
    Created on : 26-Nov-2017, 20:54:43
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/includes/header_1.jsp" %>

<div class="container">
    <p style="color: red">${message}</p>
    <h3>Login to continue</h3>
    <form action="CustomerServlet" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" name="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Login Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="exampleInputPassword1" placeholder="Enter Login Password">
        </div>
        <button type="submit" class="btn btn-primary" value="login" name="submit">Login</button>
    </form><br>
        
        
        
    <!-- Example row of columns   <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p> --> 
    <h3>Or enter your name and contact information</h3>
    <form action="CustomerServlet" method="post">
 
        <div class="form-group">
            <label for="exampleInputFirstName">First Name</label>
            <input type="text" class="form-control" id="exampleInputFirstName" name="exampleInputFirstName" placeholder="Enter First Name">
        </div>
        <div class="form-group">
            <label for="exampleInputLastName">Last Name</label>
            <input type="text" class="form-control" id="exampleInputLastName" name="exampleInputLastName" placeholder="Enter Last Name">
        </div>
        <div class="form-group">
            <label for="exampleInputAddressLine1">Address line 1</label>
            <input type="text" class="form-control" id="exampleInputAddressLine1" name="exampleInputAddressLine1" placeholder="Enter Address line 1">
        </div>
        <div class="form-group">
            <label for="exampleInputAddressLine2">Address line 2</label>
            <input type="text" class="form-control" id="exampleInputAddressLine2" name="exampleInputAddressLine2" placeholder="Enter Address line 2">
        </div>
        <div class="form-group">
            <label for="exampleInputCity">City</label>
            <input type="text" class="form-control" id="exampleInputCity" name="exampleInputCity" placeholder="Enter City">
        </div>
        <div class="form-group">
            <label for="exampleInputPostCode">Post Code</label>
            <input type="text" class="form-control" id="exampleInputPostCode" name="exampleInputPostCode" placeholder="Enter PostCode">
        </div>
        <div class="form-group">
            <label for="exampleInputCountry">Country</label>
            <input type="text" class="form-control" id="exampleInputCountry" name="exampleInputCountry" placeholder="Enter Country">
        </div>
        <div class="form-group">
            <label for="exampleInputCompany">Company</label>
            <input type="text" class="form-control" id="exampleInputCompany" name="exampleInputCompany" placeholder="Enter Company">
        </div>
        <div class="form-group">
            <label for="exampleInputCCExpiry">Credit Card Expiry</label>
            <input type="text" class="form-control" id="exampleInputCCExpiry" name="exampleInputCCExpiry" placeholder="Enter Credit Card Expiry">
        </div>
        <div class="form-group">
            <label for="exampleInputCCNumber">Credit Card Number</label>
            <input type="text" class="form-control" id="exampleInputCCNumber" name="exampleInputCCNumber" placeholder="Enter Credit Card Number">
        </div>
        <div class="form-group">
            <label for="exampleInputCCType">Credit Card Type</label>
            <select class="form-control" id="exampleInputCCType" name="exampleInputCCType">
                <option selected>VISA</option>
                <option>Mastercard</option>
                <option>Access</option>
                <option>American Express</option>
            </select>
        </div> 

        <div class="form-group">
            <label for="exampleInputEmail2">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail2" name="exampleInputEmail2" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword2">Login Password</label>
            <input type="password" class="form-control" id="exampleInputPassword2" name="exampleInputPassword2" placeholder="Enter Login Password">
        </div>
        <button type="submit" class="btn btn-primary" value="continue" name="submit">Continue</button>
    </form>

</div>

</main>
<footer class="container">
    <p>&copy; Company 2017</p>
</footer>
<!-- Bootstrap core JavaScript -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

<%@include file="/includes/footer_1.jsp" %>
