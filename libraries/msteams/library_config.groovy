fields{
    required{
        shouldNotify = Boolean
    }
    optional{
        channel{
            success = String
            failure = String
            unstable = String
        }
    }
}