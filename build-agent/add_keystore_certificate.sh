HOST=se01wstfs01.sartorius.com
PORT=443
KEYSTOREFILE=$JAVA_HOME/jre/lib/security/cacerts
KEYSTOREPASS=changeit

mkdir /secrets

openssl s_client -connect ${HOST}:${PORT} </dev/null \
    | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > /secrets/${HOST}.cert

keytool -import -noprompt -trustcacerts \
    -alias ${HOST} -file /secrets/${HOST}.cert \
    -keystore ${KEYSTOREFILE} -storepass ${KEYSTOREPASS}

keytool -list -v -keystore ${KEYSTOREFILE} -storepass ${KEYSTOREPASS} -alias ${HOST}
