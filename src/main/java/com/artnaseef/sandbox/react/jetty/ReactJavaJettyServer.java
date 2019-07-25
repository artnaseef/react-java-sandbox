package com.artnaseef.sandbox.react.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;

import java.net.URL;

/**
 * Created by art on 7/24/19.
 */
public class ReactJavaJettyServer {

    private Server jettyServer;

//========================================
// Getters and Setters
//----------------------------------------

    public Server getJettyServer() {
        return jettyServer;
    }

    public void setJettyServer(Server jettyServer) {
        this.jettyServer = jettyServer;
    }

//========================================
// Lifecycle
//----------------------------------------

    public void init() {
        if (this.jettyServer == null) {
            this.jettyServer = this.createJettyServer();

            try {
                this.jettyServer.start();
            } catch (Exception exc) {
                throw new RuntimeException("failed to startup the embedded jetty server", exc);
            }
        }
    }

//========================================
// Custom Jetty Server Setup
//----------------------------------------

    private Server createJettyServer() {
        Server result = new Server();

        this.configureJettyConnectors(result);
        this.configureJettyForStaticContent(result);

        return result;
    }

    private void configureJettyConnectors(Server server) {
        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setPort(8080);

// TBD: need an HTTPS certificate, etc.
//        ServerConnector httpsConnector = new ServerConnector(result);
//        httpConnector.setPort(8443);

        server.setConnectors(new Connector[] { httpConnector });
    }

    private void configureJettyForStaticContent(Server server) {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirAllowed(false);
        resourceHandler.setDirectoriesListed(false);

        URL staticHtmlRootUrl = ReactJavaJettyServer.class.getResource("/static-html");
        Resource staticHtmlRootResource = Resource.newResource(staticHtmlRootUrl);

        ContextHandler contextHandler = new ContextHandler("/static");
        contextHandler.setHandler(resourceHandler);
        contextHandler.setBaseResource(staticHtmlRootResource);
        contextHandler.setWelcomeFiles(new String[] {"index.html"});

        // TODO: this only supports our single handler setup (adding REST enpdoints will conflict)
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { contextHandler, new DefaultHandler() });
        server.setHandler(handlers);
    }
}
