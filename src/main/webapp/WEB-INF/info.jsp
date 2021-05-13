<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "t" tagdir = "/WEB-INF/tags/" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "spring" uri = "http://www.springframework.org/tags" %>
<t:layoutWrapper>
<input type="hidden" name="currentTab" value="${tab}">
<main class="article">
	<c:choose>
	<c:when test="${tab == 'support'}">
		<h2>To report a bug or website issue:</h2>
		<p>Email the webmaster <a href="mailto:bjandrus93@alainasteatalk.com">here</a>. Please include the date/time issue was encountered and a brief description of the issue outlining the details of the failure, the expected outcome, and any error messages you are getting.</p>
	</c:when>
	<c:when test="${tab == 'contact'}">
		<h2>Questions, Comments, Concerns?</h2>
		<p>Reach out to the editor <a href="mailto:editor@alainasteatalk.com">here</a>.</p>
	</c:when>
	<c:when test="${tab == 'donate'}">
		<h2>Want to make a difference? Stay tuned!</h2>
		<p>We're currently working on partnering with various charities to help spread awareness and advocacy. More to come soon!</p>
	</c:when>
	<c:when test="${tab == 'privacy'}">
		<h2>Privacy Policy</h2>
		<p>Last Updated: May 13, 2021</p>
		<p>Thank you for choosing to be a member of our community at www.alainasteatalk.com (the "Website"). We are committed to protecting your personal information and your right to privacy. If you have any questions or concerns about this privacy notice, or our practices with regards to your personal information, please contact the webmaster <a href="mailto:bjandrus93@alainasteatalk.com">here</a>.</p>
		<p>We take your privacy very seriously. In this privacy notice, we seek to explain to you in the clearest way possible what information we collect, how we use it and what rights you have in relation to it. We hope you take some time to read through it carefully, as it is important. If there are any terms in this privacy notice that you do not agree with, please discontinue use of our services (the "Services", which includes the Website) immediately.</p>
		<p>This privacy notice applies to all information collected through our Services, as well as any related services, sales, marketing or events.</p>
		<p><b>Please read this privacy notice carefully as it will help you understand what we do with the information that we collect.</b></p>
		<h2>What Information do We collect?</h2>
		<p>We collect personal information that you voluntarily provide to us when you register on the Website, express an interest in obtaining information about us or our products and Services, when you participate in activities on the Website (such as by posting comments), or otherwise when you contact us.</p>
		<p>The personal information that we collect depends on the context of your interactions with us and the Website, the choices you make and the products and features you use. The personal information we collect may include the following:</p>
		<p><b>Personal Information Provided by You:</b> We collect names, email addresses, passwords and other similar information.</p>
		<p>All personal information that you provide to us must be true, complete and accurate; and you must notify us of any changes to such personal information.</p>
		<h2>How do We use Your Information?</h2>
		<p>We use personal information collected via our Website for a variety of business purposes described below. We process your personal information for these purposes in reliance on our legitimate business interests, in order to enter into or perform a contract with you, with your consent, and/or for compliance with our legal obligations. We indicate the specific processing grounds we rely on next to each purpose listed below.</p>
		<p>We use the information we collect or receive:</p>
		<ul>
			<li><p><b>To facilitate account creation and logon process.</b> If you choose to link your account with us to a third-party account (such as your Google or Facebook account), we use the information you allowed us to collect from those third parties to facilitate account creation and logon process for the performance of the contract.</p></li>
			<li><p><b>To request feedback.</b> We may use your information to request feedback and to contact you about your use of our Website.</p></li>
			<li><p><b>To enable user-to-user communications.</b> We may use your information in order to enable user-to-user communications with each user's consent.</p></li>
			<li><p><b>To manage user accounts.</b> We may use your information for the purposes of managing your account and keeping it in working order.</p></li>
		</ul>
		<h2>Will Your Information be shared with anyone?</h2>
		<p>We only share information with your consent, to comply with laws, to provide you with services, to protect your rights, or to fulfill business obligations. We may process or share your data that we hold based on the following legal basis:</p>
		<ul>
			<li><p><b>Business transfers.</b> We may share or transfer your information in connection with, or during negotiations of, any merger, sale of company assets, financing, or acquisition of all or a portion of our business to another company.</p></li>
		</ul>
		<h2>How long do We keep Your Information?</h2>
		<p>We will only keep your personal information for as long as it is necessary for the purposes set out in this privacy notice, unless a longer retention period is required or permitted by law (such as tax, accounting or other legal requirements). No purpose in this notice will require us keeping your personal information for longer than the period of time in which users have an account with us.</p>
		<p>When we have no ongoing legitimate business need to process your personal information, we will either delete or anonymize such information, or, if this is not possible (for example, because your personal information has been stored in backup archives), then we will securely store your personal information and isolate it from any further processing until deletion is possible.</p>
		<h2>How do We keep Your Information safe?</h2>
		<p>We have implemented appropriate technical and organizational security measures designed to protect the security of any personal information we process. However, despite our safeguards and efforts to secure your information, no electronic transmission over the Internet or information storage technology can be guaranteed to be 100% secure; so we cannot promise or guarantee that hackers, cybercriminals, or other unauthorized third parties will not be able to defeat our security and improperly collect, access, steal or modify your information. Although we will do our best to protect your personal information, transmission of personal information to and from our Website is at your own risk. You should only access the Website within a secure environment.</p>
		<h2>What are Your Privacy Rights?</h2>
		<p>In some regions (like the EEA and UK), you have certain rights under applicable data protection laws. These may include the right (i) to request access and obtain a copy of your personal information; (ii) to request rectification or erasure; (iii) to restrict the processing of your personal information; and (iv) if applicable, to data portability. In certain circumstances, you may also have the right to object to the processing of your personal information. To make such a request, please use the contact details provided below. We will consider and act upon any request in accordance with applicable data protection laws.</p>
		<p>If we are relying on your consent to process your personal information, you have the right to withdraw your consent at any time. Please note however that this will not affect the lawfulness of the processing before its withdrawl, nor will it affect the processing of your personal information conducted in reliance on lawful processing grounds other than consent.</p>
		<p>If you are a resident of the EEA or UK and you believe we are unlawfully processing your personal information, you also have the right to complain to your local data protection supervisory authority. You can find their contact details <a href="http://ec.europa.eu/justice/data-protection/bodies/authorities/index_en.html">here</a>.</p>
		<p>If you are a resident of Switzerland, the contact details for the data protection authorities are available <a href="https://www.edoeb.admin.ch/edoeb/en/home.html">here</a>.</p>
		<h3>Account Information</h3>
		<p>If at any time you would like to review or change the information in your account or terminate your account, you can contact the webmaster using the contact information provided. Upon your request to terminate your account, we will deactivate or delete your account and information from our active databases. However, we may retain some information in our files to prevent fraud, troubleshoot problems, assist with any investigations, enforce our Terms of Use and/or comply with applicable legal requirements.</p>
		<h2>Do-Not-Track features</h2>
		<p>Most web browsers and some mobile operating systems and mobile applications include a "Do-Not-Track" (DNT) feature or setting you can activate to signal your privacy preference not to have data about your online browsing activities monitored and collected. At this stage no uniform technology standard for recognizing and implementing DNT signals has been finalized. As such, we do not currently respond to DNT browser signals or any other mechanism that automatically communicates your choice not to be tracked online. If a standard for online tracking is adopted that we must follow in the future, we will inform you about that practice in a revised version of this privacy notice.</p>
		<h2>California Residents</h2>
		<p>California Civil Code Section 1798.83, also known as the "Shine the Light" law, permits our users who are California residents to request and obtain from us, once a year and free of charge, information about categories of personal information (if any) we disclosed to third parties for direct marketing purposes and the names and addresses of all third parties with which we shared personal information in the immediately preceding calendar year. If you are a California resident and would like to make such a request, please submit your request to us in writing using the contact information provided below.</p>
		<p>If you are under 18 years of age, reside in California, and have a registered account with the Website, you have the right to request removal of unwanted data that you publicly post on the Website. To request removal of such data, please contact us using the contact information provided below; and include the email address associated with your account and a statement that you reside in California. We will make sure the data is not publicly displayed on the Website, but please be aware that the data may not be completely or comprehensively removed from all our systems.</p>
		<h3>CCPA Privacy Notice</h3>
		<p>The California Code of Regulations defines a "resident" as:</p>
		<p>(1) every individual who is in the State of California for other than a temporary or transitory purpose and</p>
		<p>(2) every individual who is domiciled in the State of California who is outside the State of California for a temporary or transitory purpose.</p>
		<p>All other individuals are defined as "non-residents".</p>
		<p>If this definition of "resident" applies to you, we must adhere to certain rights and obligations regarding your personal information.</p>
		<p><b>What categories of personal information do we collect?</b></p>
		<p>We have collected the following categories of personal information in the past twelve (12) months:</p>
		<table>
			<thead>
				<tr>
					<td>Category</td>
					<td>Examples</td>
					<td>Collected</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>A. Identifiers</td>
					<td>Contact details, such as: real name, alias, postal address, telephone or mobile contact number, unique personal identifier, online identifier, Internet Protocol address, email address and account name</td>
					<td>Yes</td>
				</tr>
				<tr>
					<td>B. Personal information categories listed in the California Customer Records statute</td>
					<td>Name, contact information, education, employment, employment history and financial information</td>
					<td>Yes</td>
				</tr>
				<tr>
					<td>C. Protected classification characteristics under California or federal law</td>
					<td>Gender and date of birth</td>
					<td>No</td>
				</tr>
				<tr>
					<td>D.Commercial information</td>
					<td>Transaction information, purchase history, financial details and payment information</td>
					<td>No</td>
				</tr>
				<tr>
					<td>E. Biometric information</td>
					<td>Fingerprints and voiceprints</td>
					<td>No</td>
				</tr>
				<tr>
					<td>F. Internet or other similar network activity</td>
					<td>Browsing history, search history, online behavior, interest data, and interactions with our and other websites, applications, systems and advertisements</td>
					<td>No</td>
				</tr>
				<tr>
					<td>G. Geolocation data</td>
					<td>Device location</td>
					<td>No</td>
				</tr>
				<tr>
					<td>H. Audio, electronic, visual, thermal, olfactory or similar information</td>
					<td>Images and audio, video or call recordings created in connection with our business activities</td>
					<td>No</td>
				</tr>
				<tr>
					<td>I. Professional or employment-related information</td>
					<td>Business contact details in order to provide you our services at a business level, job title as well as work history and professional qualifications if you apply for a job with us</td>
					<td>No</td>
				</tr>
				<tr>
					<td>J. Education information</td>
					<td>Student records and directory information</td>
					<td>No</td>
				</tr>
				<tr>
					<td>K. Inferences drawn from other personal information</td>
					<td>Inferences drawn from any of the collected personal information listed above to create a summary or profile about, for example, an individuals preferences and characteristics</td>
					<td>No</td>
				</tr>
			</tbody>
		</table>
		<p>We may also collect other personal information outside of these categories in instances where you interact with us in-person, online, or by phone or mail in the context of:</p>
		<ul>
			<li><p>Receiving help through our customer support channels</p></li>
			<li><p>Participation in customer surveys or contests</p></li>
			<li><p>Facilitation in the delivery of our Services and to respond to your inquiries</p></li>
		</ul>
		<p><b>How do we use and share your personal information?</b></p>
		<p>More information about our data collection and sharing practices can be found in this privacy notice. You may contact us by email <a href="mailto:bjandrus93@alainasteatalk.com">here</a> or by referring to the contact details at the bottom of this document.</p>
		<p>If you are using an authorized agent to exercise your right to opt-out, we may deny a request if the authorized agent does not submit proof that they have been validly authorized to act on your behalf.</p>
		<p><b>Will your information be shared with anyone else?</b></p>
		<p>We may disclose your personal information with our service providers pursuant to a written contract between us and each service provider. Each service provider is a for-profit entity that processes the information on our behalf.</p>
		<p>We may use your personal information for our own business purposes, such as for undertaking internal research for technological development and demonstration. This is not considered to be "selling" of your personal data.</p>
		<p><b>Your rights with respect to your personal data</b></p>
		<p>Right to request deletion of the data - Request to delete</p>
		<p>You may request the deletion of your personal data. If you ask us to delete your personal information, we will respect your request and delete your personal information; subject to certain exceptions provided by law such as (but not limited to): the exercise by another consumer of his or her right to free speech, our compliance requirements resulting from a legal obligation or any processing that may be required to protect against illegal activities.</p>
		<p>Right to be informed - Request to know</p>
		<p>Depending on the circumstances, you have a right to know:</p>
		<ul>
			<li><p>whether we collect and use your personal information</p></li>
			<li><p>the categories of personal information that we collect</p></li>
			<li><p>the purposes for which the collected personal information is used</p></li>
			<li><p>whether we sell your personal information to third parties</p></li>
			<li><p>the categories of personal information that we sold or disclosed for a business purpose</p></li>
			<li><p>the categories of third parties to whom the personal information was sold or disclosed for a business purpose</p></li>
			<li><p>the business or commercial purpose for collecting or selling personal information</p></li>
		</ul>
		<p>In accordance with applicable law, we are not obligated to provide or delete consumer information that is de-identified in response to a consumer request or to re-identify individual data to verify a consumer request.</p>
		<p>Right to Non-Discrimination for the Exercise of a Consumer's Privacy Rights</p>
		<p>We will not discriminate against you if you exercise your privacy rights.</p>
		<p>Verification process:</p>
		<p>Upon receiving your request, we will need to verify your identity to determine you are the same person about whom we have the information in our system. These verification efforts require us to ask you to provide information so that we can match it with information you have previously provided us. For instance, depending on the type of request you submit, we may ask you to provide certain information so that we can match the information you provide with the information we already have on file; or we may contact you through a communication method (e.g. phone or email) that you have previously provided to us. We may also use other verification methods as the circumstances dictate.</p>
		<p>We will only use personal information provided in your request to verify your identity or authority to make the request. To the extent possible, we will avoid requesting additional information from you for the purposes of verification. If, however, we cannot verify your identity from the information already maintained by us, we may request that you provide additional information for the purposes of verifying your identity and for security or fraud prevention purposes. We will delete such additionally provided information as soon as we are finished verifying you.</p>
		<p>Other privacy rights</p>
		<ul>
			<li><p>you may object to the processing of your personal data</p></li>
			<li><p>you may request correction of your personal data if it is incorrect or no longer relevant; or ask to restrict the processing of the data</p></li>
			<li><p>you can designate an authorized agent to make a request under the CCPA on your behalf. We may deny a request from an authorized agent that does not submit proof that they have been validly authorized to act on your behalf in accordance with the CCPA</p></li>
			<li><p>you may request to opt-out from future selling of your personal information to third parties. Upon receiving a request to opt-out, we will act upon the request as soon as feasibly possible, but no later than 15 days from the date of the request submission.</p></li>
		</ul>
		<p>To exercise these rights, or for other inquiries or concerns, you can contact the site administrator with the contact details below.</p>
		<h2>Changes to this Notice</h2>
		<p>We may update this privacy notice from time to time. The updated version will be indicated by an updated "Revised" date and will be effective as soon as it is accessible on the Website. If we make material changes to this privacy notice, we may notify you either by prominently posting a notice of such changes or by directly sending you a notification. We encourage you to review this privacy notice frequently to be informed of how we are protecting your information.</p>
		<h2>Contact Info</h2>
		<p>If you have questions or comments about this notice, or would like to exercise your rights herein, you may contact the site administrator at <a href="mailto:bjandrus93@alainasteatalk.com">bjandrus93@alainasteatalk.com</a></p>
	</c:when>
	</c:choose>
</main>
</t:layoutWrapper>