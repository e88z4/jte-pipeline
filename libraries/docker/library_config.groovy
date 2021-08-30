fields{
    required{
        tag = String
        dockerfile = String
        credential_id = String
    }
    optional{
        build_context = String
        build_args = String
    }
}