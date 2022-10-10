CREATE TABLE authorizationConsent (
    registeredClientId nvarchar(255) NOT NULL,
    principalName nvarchar(255) NOT NULL,
    authorities nvarchar(1000) NOT NULL,
    PRIMARY KEY (registeredClientId, principalName)
);
