import { useForm } from '@inertiajs/react';
import { Record } from './types';

export default function Show({ record }: { record: Record }) {
  const { delete: deleteRecord } = useForm();

  const handleDelete = (e: React.MouseEvent) => {
    e.preventDefault();
    if (window.confirm(`Are you sure you want to delete ${record.name}?`)) {
      deleteRecord(`/records/${record.id}`);
    }
  };

  const handleGoBack = () => {
    window.history.back();
  };

  return (
    <main className="container">
      <button 
        className="outline contrast" 
        onClick={handleGoBack} 
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
      </button>
      <br/>

      <article className="text-center" style={{paddingBottom: '50px'}}>
        {record.coverImage && (
          <img
            src={record.coverImage}
            alt={`Cover of ${record.name}`}
            className="cover-image"
            style={{ maxWidth: '300px', marginBottom: '20px' }}
          />
        )}

        <h3>{record.name}</h3>
        <h5>{record.artist} ({record.yearOfRelease})</h5>

        <a
          href="#"
          onClick={handleDelete}
          style={{float: 'right'}}
        >
          Delete
        </a>
      </article>
    </main>
  );
}
