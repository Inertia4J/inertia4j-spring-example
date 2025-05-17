import { useForm } from '@inertiajs/react';
import { Record } from './types';
import { BackButton } from '../../components/BackButton';

export default function Show({ record }: { record: Record }) {
  const { delete: deleteRecord } = useForm();

  const handleDelete = (e: React.MouseEvent) => {
    e.preventDefault();
    if (window.confirm(`Are you sure you want to delete ${record.name}?`)) {
      deleteRecord(`/records/${record.id}`);
    }
  };

  return (
    <main className="container">
      <BackButton href="/records" />

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
          style={{ float: 'right' }}
        >
          Delete
        </a>
      </article>
    </main>
  );
}
