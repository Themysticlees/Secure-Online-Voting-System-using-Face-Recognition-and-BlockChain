<?php
 
    // Put your MailChimp API and List ID hehe
    $api_key = '63f2ab3c0d503d6819edfbae6768281f-us18';
    $list_id = 'cc0ee8140e';
 
    // Let's start by including the MailChimp API wrapper
    include('MCAPI.class.php');
    // Then call/use the class
    use \DrewM\MailChimp\MailChimp;
    $MailChimp = new MailChimp($api_key);
 
    // Submit subscriber data to MailChimp
    // For parameters doc, refer to: http://developer.mailchimp.com/documentation/mailchimp/reference/lists/members/
    // For wrapper's doc, visit: https://github.com/drewm/mailchimp-api
    $result = $MailChimp->post("lists/$list_id/members", [
                            'email_address' => $_POST["email"],
                            // 'merge_fields'  => ['FNAME'=>$_POST["fname"], 'LNAME'=>$_POST["lname"]],
                            'status'        => 'subscribed',
                        ]);
 
    if ($MailChimp->success()) {
        // Success message
        echo "<h4>Thank you, you have been added to our mailing list.</h4>";
    } else {
        // Display error
        echo $MailChimp->getLastError();
        // Alternatively you can use a generic error message like:
        // echo "<h4>Please try again.</h4>";
    }
?>