# project
=======================================================
Mandatory JDK version* 1.8+
Setup:
1. import into eclipse/sts as maven project
2. change hibernate.hbm2ddl.auto=create in application.properties or use sql file to create database/tables.
3. start spring boot appplication, started class: ATCApplication (run as standalone main program)

=======================================================

Step1:(user needs jwt token authentication)
Request endpoing uri:
http://localhost:9090/authenticate

Response:
{
    "AuthToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdGMiLCJleHAiOjE1OTUyMzQyNDMsImlhdCI6MTU5NDkzNDI0M30.hN062kUg3tExv1iW4tp2xn1is8OpWuhmUuDgu8z_BOQ"
}

Step2: copy token and add in create users and fetch users request header
=======================================================
Step3:
Sample Request for create single/bulk user
http://localhost:9090/api/v1/create/users
sample auth header**:
key: Authorization
value: Auth-<copy paste you authtoken> (eg: Auth-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhdGMiLCJleHAiOjE1OTUyMzQyNDMsImlhdCI6MTU5NDkzNDI0M30.hN062kUg3tExv1iW4tp2xn1is8OpWuhmUuDgu8z_BOQ)

{
    "userData": [
        {
            "id": 100,
            "name": "testuser1",
            "locations": [
                {
					"latitude": "80.1118231",
                    "longitude": "12.9299028"
                },
				{
					"latitude": "80.1118231",
                    "longitude": "12.9299028"
                }
            ]
        },
         {
            "id": 200,
            "name": "testuser2",
            "locations": [
                {
                    "latitude": "80.169937",
                    "longitude": "12.914600"
                }
            ]
        },
         {
            "id": 300,
            "name": "testuser3",
            "locations": [
                {
                    "latitude": "80.2231951",
                    "longitude": "12.9879107"
                }
            ]
        },
		{
            "id": 400,
            "name": "testuser3",
            "locations": [
                {
                    "latitude": "35.929673",
                    "longitude": "-78.948237"
                },
				{
                    "latitude": "38.889510",
                    "longitude": "-77.032000"
                },
				{
                    "latitude": "12.9879107",
                    "longitude": "80.2231951"
                },
				{
                    "latitude": "13.0872004",
                    "longitude": "80.2164421"
                }				
            ]
        }
    ]
}

Sample Response:
Number of users created: 4
=======================================================
Step4:

Fetch matching proximity(10km radius) users,(can be specified in application.properties)

Sample Request:
http://localhost:9090/api/v1/search/nearby?langitude=12.917143&latitude=80.192352

Sample Response:
{
    "userData": [
        {
            "id": 400,
            "name": "testuser3",
            "locations": [
                {
                    "latitude": 35.929673,
                    "longitude": -78.948237
                },
                {
                    "latitude": 38.88951,
                    "longitude": -77.032
                },
                {
                    "latitude": 12.9879107,
                    "longitude": 80.2231951
                },
                {
                    "latitude": 13.0872004,
                    "longitude": 80.2164421
                }
            ]
        }
    ]
}

=======================================================
updating/adding existing users new location

Sample Request:
http://localhost:9090/api/v1/create/users

{
    "userData": [
        {
            "id": 100,
            "name": "testuser1",
            "locations": [
                {
					"latitude": "80.1118231",
                    "longitude": "12.9299028"
                },
				{
					"latitude": "80.1118231",
                    "longitude": "12.9299028"
                },
                {
					"latitude": "12.9299028",
                    "longitude": "80.1118231"
                }
            ]
        }
    ]
}

Sample Response:
Number of users created: 1

=======================================================