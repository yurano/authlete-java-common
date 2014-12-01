/*
 * Copyright (C) 2014 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;
import com.authlete.common.types.Sns;


/**
 * Authentication request from Authlete to a service implementation.
 *
 * @author Takahiko Kawasaki
 *
 * @since 1.1
 */
public class AuthenticationCallbackRequest implements Serializable
{
    private static final long serialVersionUID = 3L;


    private long serviceApiKey;
    private long clientId;
    private String id;
    private String password;
    private String[] claims;
    private String[] claimsLocales;

    private Sns sns;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private String rawTokenResponse;


    /**
     * Get the API key of the target service.
     *
     * <p>
     * This property is always set when Authlete makes a request.
     * </p>
     *
     * @return
     *         The API key of the target service.
     */
    public long getServiceApiKey()
    {
        return serviceApiKey;
    }


    /**
     * Set the API key of the target service.
     *
     * @param apiKey
     *         The API key of the target service.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackRequest setServiceApiKey(long apiKey)
    {
        this.serviceApiKey = apiKey;

        return this;
    }


    /**
     * Get the ID of the client application that triggered this
     * authentication request.
     *
     * <p>
     * This property is always set when Authlete makes a request.
     * </p>
     *
     * @return
     *         The ID of the client application.
     */
    public long getClientId()
    {
        return clientId;
    }


    /**
     * Set the ID of the client application that triggered this
     * authentication request.
     *
     * @param clientId
     *         The ID of the client application.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackRequest setClientId(long clientId)
    {
        this.clientId = clientId;

        return this;
    }


    /**
     * Get the ID of the end-user to authenticate.
     *
     * <p>
     * When the value of {@code sns} property is {@code null},
     * this property holds the value of the login ID that the end-user
     * has entered to the login ID field in the UI of the <a href=
     * "https://tools.ietf.org/html/rfc6749#section-3.1">authorization
     * endpoint</a>, or the value of {@code username} request parameter
     * to the <a href="https://tools.ietf.org/html/rfc6749#section-3.2"
     * >token endpoint</a> in the case of <a href=
     * "https://tools.ietf.org/html/rfc6749#section-4.3">Resource
     * Owner Password Credentials</a> flow.
     * </p>
     *
     * <p>
     * On the other hand, if {@code sns} property is not {@code null},
     * this property holds the subject (= unique identifier) of the
     * end-user in the SNS.
     * </p>
     *
     * <p>
     * This property is always set when Authlete makes a request.
     * </p>
     *
     * @return
     *         The ID of the end-user to authenticate.
     */
    public String getId()
    {
        return id;
    }


    /**
     * Set the ID of the end-user to authenticate.
     *
     * @param id
     *         The ID of the end-user to authenticate.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackRequest setId(String id)
    {
        this.id = id;

        return this;
    }


    /**
     * Get the password of the end-user to authenticate.
     *
     * <p>
     * This property holds the value of the password that the end-user
     * has entered to the password field in the UI of the <a href=
     * "https://tools.ietf.org/html/rfc6749#section-3.1">authorization
     * endpoint</a>, or the value of {@code password} request parameter
     * to the <a href="https://tools.ietf.org/html/rfc6749#section-3.2"
     * >token endpoint</a> in the case of <a href=
     * "https://tools.ietf.org/html/rfc6749#section-4.3">Resource
     * Owner Password Credentials</a> flow.
     * </p>
     *
     * <p>
     * If {@code sns} property is {@code null}, it is ensured that this
     * property is not {@code null}. In such a case, authentication
     * should be performed on the pair of {@link #id} property and this
     * {@code password} property. On the other hand, if {@code sns}
     * property is not {@code null}, this property has no meaning,
     * because authentication has been performed by the SNS.
     * </p>
     *
     * @return
     *         The password of the end-user to authenticate.
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * Set the password of the end-user to authenticate.
     *
     * @param password
     *         The password of the end-user to authenticate.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackRequest setPassword(String password)
    {
        this.password = password;

        return this;
    }


    /**
     * Get the list of claims requested by a client application.
     *
     * <p>
     * A <i>claim</i> is a piece of information about an end-user.
     * Some standard claim names such as {@code given_name} and
     * {@code email} are defined in "<a href=
     * "http://openid.net/specs/openid-connect-core-1_0.html#StandardClaims"
     * >5.1. Standard Claims</a>" in <a href=
     * ""http://openid.net/specs/openid-connect-core-1_0.html">OpenID
     * Connect Core 1.0</a>. A service implementation should extract
     * data corresponding to the claims from its database and return
     * them to Authlete. The data will be embedded in an ID token.
     * </p>
     *
     * <p>
     * Note that a claim name may be followed by <code>#<i>locale</i></code>.
     * For example, <code>family_name#ja</code>. See "<a href=
     * "http://openid.net/specs/openid-connect-core-1_0.html#ClaimsLanguagesAndScripts"
     * >5.2. Claims Languages and Scripts</a>" in <a href=
     * "http://openid.net/specs/openid-connect-core-1_0.html">OpenID
     * Connect Core 1.0</a> for details.
     * </p>
     *
     * <p>
     * This property is null when claim data are not necessary
     * (= when an ID token is not necessary to be generated).
     * </p>
     *
     * @return
     *         The list of claims requested by a client application.
     */
    public String[] getClaims()
    {
        return claims;
    }


    /**
     * Set the list of claims requested by a client application.
     *
     * @param claims
     *         The list of claims requested by a client application.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackRequest setClaims(String[] claims)
    {
        this.claims = claims;

        return this;
    }


    /**
     * Get the list of locales for claims.
     *
     * <p>
     * This property holds the value of {@code claims_locales} request
     * parameter contained in an authorization request. The values are
     * the end-user's preferred languages and scripts for claims. See
     * "<a href="http://openid.net/specs/openid-connect-core-1_0.html#ClaimsLanguagesAndScripts"
     * >5.2. Claims Languages and Scripts</a>" in <a href=
     * "http://openid.net/specs/openid-connect-core-1_0.html">OpenID
     * Connect Core 1.0</a> for details.
     * </p>
     *
     * <p>
     * This property is null when claim data are not necessary
     * (= when an ID token is not necessary to be generated).
     * </p>
     *
     * @return
     *         The list of locales for claims.
     */
    public String[] getClaimsLocales()
    {
        return claimsLocales;
    }


    /**
     * Set the list of locales for claims.
     *
     * @param claimsLocales
     *         The list of locales for claims.
     *
     * @return
     *         {@code this} object.
     */
    public AuthenticationCallbackRequest setClaimsLocales(String[] claimsLocales)
    {
        this.claimsLocales = claimsLocales;

        return this;
    }


    /**
     * Get the SNS that the end-user used for social login.
     *
     * @return
     *         The SNS that the end-user used for social login.
     *         {@code null} is returned if the end-user did not
     *         use social login.
     *
     * @since 1.2
     */
    public Sns getSns()
    {
        return sns;
    }


    /**
     * Set the SNS that the end-user used for social login.
     *
     * @param sns
     *         The SNS that the end-user used for social login.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.2
     */
    public AuthenticationCallbackRequest setSns(Sns sns)
    {
        this.sns = sns;

        return this;
    }


    /**
     * Get the access token returned by the SNS which the end-user
     * used for social login.
     *
     * @return
     *         The access token returned by the SNS. {@code null}
     *         is returned when social login was not used.
     *
     * @since 1.2
     */
    public String getAccessToken()
    {
        return accessToken;
    }


    /**
     * Set the access token returned by the SNS which the end-user
     * used for social login.
     *
     * @param accessToken
     *         The access token returned by the SNS.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.2
     */
    public AuthenticationCallbackRequest setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;

        return this;
    }


    /**
     * Get the refresh token returned by the SNS which the end-user
     * used for social login.
     *
     * @return
     *         The refresh token returned by the SNS. {@code null}
     *         is returned when social login was not used or if
     *         the SNS did not return a refresh token.
     *
     * @since 1.2
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }


    /**
     * Set the refresh token returned by the SNS which the end-user
     * used for social login.
     *
     * @param refreshToken
     *         The refresh token returned by the SNS.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.2
     */
    public AuthenticationCallbackRequest setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;

        return this;
    }


    /**
     * Get the lifetime of the access token in seconds.
     *
     * @return
     *         The lifetime of the access token in seconds. 0 is returned
     *         when social login was not used of if the SNS did not return
     *         information about lifetime of the access token.
     *
     * @since 1.2
     */
    public long getExpiresIn()
    {
        return expiresIn;
    }


    /**
     * Set the lifetime of the access token in seconds.
     *
     * @param expiresIn
     *         The lifetime of the access token in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.2
     */
    public AuthenticationCallbackRequest setExpiresIn(long expiresIn)
    {
        this.expiresIn = expiresIn;

        return this;
    }


    /**
     * Get the raw response from the token endpoint of the SNS.
     *
     * <p>
     * If the SNS complies with RFC 6749, the format is JSON.
     * Note that Facebook returns application/x-www-form-urlencoded.
     * </p>
     *
     * @return
     *         The raw response from the token endpoint of the SNS.
     *
     * @since 1.2
     */
    public String getRawTokenResponse()
    {
        return rawTokenResponse;
    }


    /**
     * Set the raw response from the token endpoint of the SNS.
     *
     * @param response
     *         The raw response from the token endpoint of the SNS.
     *
     * @return
     *         {@code this} object.
     *
     * @since 1.2
     */
    public AuthenticationCallbackRequest setRawTokenResponse(String response)
    {
        this.rawTokenResponse = response;

        return this;
    }
}
