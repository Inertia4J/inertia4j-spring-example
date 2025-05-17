import { Link } from "@inertiajs/react";

export const BackButton = ({ href }: { href: string }) => (
  <Link
    href={href}
    as="button"
    className="outline contrast"
    style={{ display: 'flex', alignItems: 'center', gap: '8px', padding: '10px', paddingRight: '15px' }} // Add styling to align the icon and text
  >
    <svg
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
      style={{ width: '20px', height: '20px' }} // Ensure the icon size is good
    >
      <path d="M15 6l-6 6l6 6" />
    </svg>
    Go back
  </Link>
);
