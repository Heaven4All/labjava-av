package com.aviva.ciso.sdlc.validation.esapi;
import org.owasp.esapi.Encoder;
import org.owasp.esapi.EncoderConstants;
import org.owasp.esapi.StringUtilities;
import org.owasp.esapi.errors.ValidationException;
import org.owasp.esapi.reference.validation.BaseValidationRule;

public class TokenValidationRule extends BaseValidationRule {

	// token that contains 32 alphanumeric characters.
	private static final String TOKEN_FORMAT = "\\w{32}";
	
	public TokenValidationRule(String typeName, Encoder encoder) {
		super(typeName, encoder);
	}

	@Override
	public String getValid(String context, String input) throws ValidationException {
		if (StringUtilities.isEmpty(input)) {
			throw new ValidationException(context + ": Input token required",
					"Input token required: context=" + context + ", input=" + input, context );
		}
		if (!isValidToken(input)) {
			throw new ValidationException(context + ": Input token invalid",
					"Input token invalid: context=" + context + ", input=" + input, context );
		}
		return input;
	}

	private static boolean isValidToken(String input) {
		return input.matches(TOKEN_FORMAT);
	}

	@Override
	protected String sanitize(String context, String input) {
		return whitelist(input, EncoderConstants.ALPHANUMERICS);
	}
}
