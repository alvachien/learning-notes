CREATE TABLE client (
    id nvarchar(255) NOT NULL,
    clientId nvarchar(255) NOT NULL,
    clientIdIssuedAt datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
    clientSecret nvarchar(255) DEFAULT NULL,
    clientSecretExpiresAt datetime DEFAULT NULL,
    clientName nvarchar(255) NOT NULL,
    clientAuthenticationMethods nvarchar(1000) NOT NULL,
    authorizationGrantTypes nvarchar(1000) NOT NULL,
    redirectUris nvarchar(1000) DEFAULT NULL,
    scopes nvarchar(1000) NOT NULL,
    clientSettings nvarchar(2000) NOT NULL,
    tokenSettings nvarchar(2000) NOT NULL,
    PRIMARY KEY (id)
);