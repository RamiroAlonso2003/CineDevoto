import logoDevoto from './logoDevoto.svg';

function LogoIcon({ size = 40 }) {
  return (
    <img
      src={logoDevoto}
      alt="Cinema Devoto"
      height={size}
      style={{ objectFit: 'contain' }}
    />
  );
}

export default LogoIcon;
