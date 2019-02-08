package com.changr.security;

import com.auth0.jwk.UrlJwkProvider;

import java.net.MalformedURLException;
import java.net.URL;

public class CognitoJwkProvider extends UrlJwkProvider
{
    public CognitoJwkProvider(String issuer)
    {
        super(CognitoJwkProvider.urlForIssuer(issuer));
    }

    private static URL urlForIssuer(String issuer)
    {
        try {
            return new URL(issuer + "/.well-known/jwks.json");
        } catch (MalformedURLException error) {
            throw new IllegalArgumentException("Invalid JWKS URI.", error);
        }
    }
}


