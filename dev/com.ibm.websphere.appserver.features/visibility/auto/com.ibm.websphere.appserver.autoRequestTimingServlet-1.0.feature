-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=com.ibm.websphere.appserver.autoRequestTimingServlet-1.0
Manifest-Version: 1.0
IBM-Provision-Capability: osgi.identity; filter:="(&(type=osgi.subsystem.feature)(osgi.identity=com.ibm.websphere.appserver.requestTiming-1.0))", \
 osgi.identity; filter:="(&(type=osgi.subsystem.feature)(|(osgi.identity=com.ibm.websphere.appserver.servlet-3.0)(osgi.identity=com.ibm.websphere.appserver.servlet-3.1)(osgi.identity=com.ibm.websphere.appserver.servlet-4.0)(osgi.identity=com.ibm.websphere.appserver.servlet-5.0)(osgi.identity=io.openliberty.servlet.internal-6.0)(osgi.identity=io.openliberty.servlet.internal-6.1)))"
IBM-Install-Policy: when-satisfied
-bundles=com.ibm.ws.request.timing.servlet
kind=ga
edition=core
