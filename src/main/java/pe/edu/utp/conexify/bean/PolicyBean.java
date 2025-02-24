package pe.edu.utp.conexify.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import pe.edu.utp.conexify.config.PolicyType;
import pe.edu.utp.conexify.dto.PoliciesDTO;
import pe.edu.utp.conexify.util.Bundle;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Named
@RequestScoped
public class PolicyBean implements Serializable {
    private List<PoliciesDTO> policies;
    private String dialogTitle;

    public void loadPolicies(String policyType) {
        switch (Objects.requireNonNull(PolicyType.fromString(policyType))) {
            case PRIVACY:
                dialogTitle = Bundle.getAttributeI18N("dialog_privacy_title");
                policies = List.of(
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_privacy_1_title"))
                                .description(Bundle.getAttributeI18N("policy_privacy_1_description"))
                                .build(),
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_privacy_2_title"))
                                .description(Bundle.getAttributeI18N("policy_privacy_2_description"))
                                .build()
                );
                break;
            case TERMS:
                dialogTitle = Bundle.getAttributeI18N("dialog_terms_title");
                policies = List.of(
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_terms_1_title"))
                                .description(Bundle.getAttributeI18N("policy_terms_1_description"))
                                .build(),
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_terms_2_title"))
                                .description(Bundle.getAttributeI18N("policy_terms_2_description"))
                                .build()
                );
                break;
            case COOKIES:
                dialogTitle = Bundle.getAttributeI18N("dialog_cookies_title");
                policies = List.of(
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_cookies_1_title"))
                                .description(Bundle.getAttributeI18N("policy_cookies_1_description"))
                                .build(),
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_cookies_2_title"))
                                .description(Bundle.getAttributeI18N("policy_cookies_2_description"))
                                .build()
                );
                break;
            case COMMUNITY:
                dialogTitle = Bundle.getAttributeI18N("dialog_community_title");
                policies = List.of(
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_community_1_title"))
                                .description(Bundle.getAttributeI18N("policy_community_1_description"))
                                .build(),
                        PoliciesDTO.builder()
                                .title(Bundle.getAttributeI18N("policy_community_2_title"))
                                .description(Bundle.getAttributeI18N("policy_community_2_description"))
                                .build()
                );
                break;
        }
    }

}
