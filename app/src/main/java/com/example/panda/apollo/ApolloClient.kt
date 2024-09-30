package com.example.panda.apollo
import  com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("http://192.168.1.245:7000/graphql")
    .build()