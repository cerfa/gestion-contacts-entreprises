package fenncim.genba.genesis.gce.model;

public enum ContactType {
    EMPLOYEE("employee"),
    FREELANCE("freelance");
    private final String contactTypeDesc;

    ContactType(String authProvider) {
        this.contactTypeDesc = authProvider;
    }

    public static ContactType getAuthProviderFromString(String contactTypeDesc) {
        for(ContactType authProvider1 : ContactType.values()) {
            if(authProvider1.toString().equalsIgnoreCase(contactTypeDesc)) {
                return authProvider1;
            }
        }
        throw new IllegalArgumentException("contact type not managed");
    }

    public String getContactTypeDesc() {
        return this.contactTypeDesc;
    }
}
