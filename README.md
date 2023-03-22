# AWS SES Email Service PoC


## AWS SES
We need access and secret keys with the following actions for the application in order to use SES:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ses:SendEmail",
                "ses:SendRawEmail"
            ],
            "Resource": "*"
        }
    ]
}
```
 Also, a verified email address or domain is necessary. It can easily be done by following instructions in the AWS Console. Ir order to verify the domain, it is required some extra steps since it is based on DomainKeys Identified Mail (DKIM). It will also be necessary to ask AWS for production access in the SES service. AWS does this in order to prevent new accounts to spam people.
