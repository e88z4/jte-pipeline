package libraries.msteams.steps

@CleanUp({ return config.shouldNotify })
void call(){
    switch(currentBuild.result){
        case null: //no result set yet means success
        case "SUCCESS":
            office365ConnectorSend webhookUrl: "${config.channel.success}"
            break;
        case "FAILURE":
            office365ConnectorSend webhookUrl: "${config.channel.failure}"
            break;
        case "UNSTABLE":
            office365ConnectorSend webhookUrl: "${config.channel.unstable}"
            break;
        default:
            echo "Microsoft Team notifier does nothing: ${currentBuild.result}"
    }
}