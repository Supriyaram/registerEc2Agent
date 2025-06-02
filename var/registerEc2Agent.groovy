import hudson.slaves.*
import hudson.plugins.sshslaves.*
import hudson.slaves.*
import hudson.model.*
import jenkins.model.Jenkins

def call(String nodeName, String privateIP, String credentialsId) {
    def launcher = new SSHLauncher(privateIP, 22, credentialsId)
    def node = new DumbSlave(
        nodeName, "/home/ubuntu/jenkins", launcher
    )
    node.setLabelString(nodeName)
    node.setNumExecutors(1)
    node.setRetentionStrategy(new RetentionStrategy.Always())

    Jenkins.instance.addNode(node)
}
