package org.example;

/**
 * two networking model:
 * OSI model - 7 layers model
 *
 * application layer: some interface user can use it
 * presentation layer: data encryption and decryption, data compression, data formatting
 * session layers: it does establish, coordinate, maintenance, terminate the connection between two devices / nodes
 * transport layer: provides some informations about your data transmission. it offers some way to track of your connection
 * networking layer:IP4, IP6 -> you can do routing, logical addressing
 *                  this layer will transfer data sequence from one node to another node between different networking
 * data link layer: in this layer, all data cen be encoded into bits  and manage data transfer. error control and flow control
 * physical layer: it converts data bits into electrical impulses or radio signals -> fiber cable
 *
 *
 * TCP/IP model -> four layers model
 * application layer: same with 7 layer model
 * transport layer: end-to-end communication and data transfer management between two hosts / node
 * network layer: same with networking layer in 7 layers model
 * network access layer: more like conbination of data link layer and physical layer
 *
 *
 *
 *
 *
 *
 *
 *
 * TCP connection
 * terms:
 *      SYN: used to establish the connection
 *      ACK: let us know the other side that it has received the SYN
 *      ACKnum:next sequence number that receiver expects to receive
 *      FIN: to terminate the connection
 *      SYN-ACK: SYN message from local device and ACK of earlier packet
 *      SEQ: keep track how many data is has sent and make sure data is delivered in the connect order
 *
 *
 *
 *  client state            your device                     other device            server state
 *  LISTEN                                                                              LISTEN
 *
 *                          SYN=1 + SEQ = X->
 *  SYNSENT
 *                                                          as long as receive SYN      SYN RCVD
 *                                           <-ACK = 1, SYN  =1, ACKnum = X + 1 SEQ = y
 *   ESTAB                  as long as you receive ACK
 *
 *                          ACK = 1, ACKnum = y+1 ->
 *                                                          as long as receive ACK         ESTAB
 *
 *      1: we want to know target device is alive or ready to receive data
 *      2: we donot want to build duplicate connection
 *
 *      what if the first syn number is not received by target device? -> first handshake is lost
 *      the client will resend syn number again
 *      what if the second syn number is not received by target device? -> second handshake is lost
 *      first: client side will resend syn(first handshake), second target side will resend syn(second handshake)
 *
 *
 *      TCP Connection terminate
 *
 *      client state                    your device                     target device                       server state
 *          ESTAB        soket.close()   FIN =1, seq =X ->                                                       ESTAB
 *          FIN_WAIT_1                   as long as you send fin
 *                                                                   as long as receive FIN                     CLOSE_WAIT
 *         FIN_WAIT_2                                                 <-ACK =1, ACKnum= x + 1
 *            ****************************************************************************************************
 *                                                  data still can send from target device
 *            ****************************************************************************************************
 *                                                          when all data is sent, FIN = 1, Seq = y will be send  LAST_ACK
 *           TIMED_WAIT      receive FIN and send last ACK = 1->
 *                                                                      receive last FIN and close connection       CLOSE
 *
 *            CLOSE         after amount of time, state is changed in any way
 *
 *
 *     if the first handshake is lost
 *     the client will resend fin to service side
 *     if the second one is lost
 *     the client will resend fin and service will resend ack
 *     when the fin that send from target device
 *     target device will resend fin
 *
 *     UDP:
 *     what is UDP
 *      it sends packets directly to target device
 *      if you have time-sensitive concerns, udp is the best choice.
 *      it is faster but not reliable than TCP
 *
 *    DNS:
 *    what is DNS:-> system that used to translate url to ip address
 *      1: user type : www.google.com
 *      2: your webroswer will check this url is cached or not
 *              if yes, will return 123.123.123.123
 *      3: if not, this request will send to local DNS server: this url will send to ATT,Tmobile
 *              if provider has address: then return 123.123.123.13
 *       4: if not, this request will send to root dns server
 *        5: root server will return 123.123.123.123
 *
 *
 *    HTTP:
 *    it is a protocal that allows us to deliver or fetch data, such as html, video, text, pictures.
 *
 *    stateless: your request will be treated independently
 *
 *    http methods:
 *      get: retrieves data from your db/ some resources
 *      head: similar to get, but it only retrieves the headers of a resource
 *      post:send date to the resources to create or update a resource
 *      put: replace data
 *      delete: delete data
 *      patch: partial modification to a resource
 *      ....
 *
 *     *** http status code:
 *         1XX: informational
 *              100: Continue -> the target received your request, and the client can send another request to process further
 *
 *         2XX: success
 *              the client's reuqest was successfully received, understood and accepted
 *              200: ok
 *              201: created
 *              204:No content:
 *         3XX:redirection
 *              301: MOVED PERMANENTLY:
 *              302: the sever found some new url: need to redirection. and response can be used for redirecting the client to a new url
 *
 *
 *         4XX: client error
 *              bad request
 *              400: bad request:
 *              401:
 *              404: not found
 *
 *         5XX: server error
 *              the server failed to take your request
 *              500: internal server error
 *              503: service is not available
 *
 *        HTTPS
 *          what is symmetric algo? -> both client side and server side use the same key to encrypt data
 *
 *          what is asymmetric algo? -> client side and server side use different key to do encryption: public key and private key
 *
 *
 *          HTTPS uses asymmetric algo
 *          https = http + ssl/tls
 *
 *          step 1: https://sss.com ->
 *          step 2: ssl/tls certificate is sent by server side to client side -> this certificate has: server's public key and certificate authority(CA)
 *          step 3: your client side will do certificate verification -> CA is trusted or certificate is not expired
 *          step 4: the client side will generate pre-master secret for the session key exchange
 *          step 5: key exchange: the client will encrypt pre master secret with server's public key
 *                              only the trusted server can decrypt this with private key
 *          step 6: both sides use pre master secret to generate session key
 *          last step: everyone is ready to start
 *
 */
public class Day7 {
}
